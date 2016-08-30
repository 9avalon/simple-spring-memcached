/*
 * Copyright (c) 2012-2016 Nelson Carpentier, Jakub Białek
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */

package com.google.code.ssm.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;

import com.google.code.ssm.aop.support.AnnotationData;
import com.google.code.ssm.aop.support.AnnotationDataBuilder;
import com.google.code.ssm.api.format.SerializationType;

/**
 * 
 * @author Nelson Carpentier
 * @author Jakub Białek
 * @since 2.0.0
 * 
 * @param <T>
 *            the type of SSM read from cache annotation
 */
abstract class SingleReadCacheAdvice<T extends Annotation> extends CacheAdvice {

    private final Class<T> annotationClass;

    protected SingleReadCacheAdvice(final Class<T> annotationClass) {
        this.annotationClass = annotationClass;
    }

    // pjp获取当前正在执行的方法
    protected Object cache(final ProceedingJoinPoint pjp) throws Throwable {
        if (isDisabled()) {
            getLogger().info("Cache disabled");
            return pjp.proceed();
        }
        // This is injected caching. If anything goes wrong in the caching, LOG
        // the crap outta it, but do not let it surface up past the AOP injection itself.
        final T annotation;
        final AnnotationData data;
        final SerializationType serializationType;
        String cacheKey = null;
        try {
            final Method methodToCache = getCacheBase().getMethodToCache(pjp);
            getCacheBase().verifyReturnTypeIsNoVoid(methodToCache, annotationClass);

            annotation = methodToCache.getAnnotation(annotationClass);

            // 查找序列化方式，如果没注解返回null
            serializationType = getCacheBase().getSerializationType(methodToCache);
            data = AnnotationDataBuilder.buildAnnotationData(annotation, annotationClass, methodToCache);

            // 生成key
            cacheKey = getCacheKey(data, pjp.getArgs(), methodToCache.toString());

            // 查找缓存中值是否存在
            final Object result = getCacheBase().getCache(data).get(cacheKey, serializationType);
            if (result != null) {
                getLogger().debug("Cache hit.");
                return getCacheBase().getResult(result);
            }
        } catch (Exception ex) {
            warn(ex, "Caching on method %s and key [%s] aborted due to an error.", pjp.toShortString(), cacheKey);
            return pjp.proceed();
        }

        // 方法执行
        final Object result = pjp.proceed();

        // This is injected caching. If anything goes wrong in the caching, LOG
        // the crap outta it, but do not let it surface up past the AOP injection itself.
        try {
            // 如果返回结果为空，则不直接存空，存项目里面的一个定义好的代替null
            final Object submission = getCacheBase().getSubmission(result);
            getCacheBase().getCache(data).set(cacheKey, data.getExpiration(), submission, serializationType);
        } catch (Exception ex) {
            warn(ex, "Caching on method %s and key [%s] aborted due to an error.", pjp.toShortString(), cacheKey);
        }
        return result;
    }


    protected abstract String getCacheKey(final AnnotationData data, final Object[] args, final String methodDesc) throws Exception;

}
