/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.nifi.attribute.expression.language.evaluation.functions;

import java.util.Map;

import org.apache.nifi.attribute.expression.language.evaluation.EvaluationContext;
import org.apache.nifi.attribute.expression.language.evaluation.Evaluator;
import org.apache.nifi.attribute.expression.language.evaluation.QueryResult;
import org.apache.nifi.attribute.expression.language.evaluation.StringEvaluator;
import org.apache.nifi.attribute.expression.language.evaluation.StringQueryResult;

public class PrependEvaluator extends StringEvaluator {

    private final Evaluator<String> subject;
    private final Evaluator<String> prependEvaluator;

    public PrependEvaluator(final Evaluator<String> subject, final Evaluator<String> prepend) {
        this.subject = subject;
        this.prependEvaluator = prepend;
    }

    @Override
    public QueryResult<String> evaluate(final Map<String, String> attributes, final EvaluationContext context) {
        final String subjectValue = subject.evaluate(attributes, context).getValue();
        final String prependValue = prependEvaluator.evaluate(attributes, context).getValue();

        final String result = (prependValue == null ? "" : prependValue) + (subjectValue == null ? "" : subjectValue);
        return new StringQueryResult(result);
    }

    @Override
    public Evaluator<?> getSubjectEvaluator() {
        return subject;
    }

}
