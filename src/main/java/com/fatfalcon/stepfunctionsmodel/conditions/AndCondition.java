/*
 * Copyright 2010-2017 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.fatfalcon.stepfunctionsmodel.conditions;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatfalcon.stepfunctionsmodel.internal.Buildable;
import com.fatfalcon.stepfunctionsmodel.internal.PropertyNames;
import com.fatfalcon.stepfunctionsmodel.states.Choice;
import com.fatfalcon.stepfunctionsmodel.states.ChoiceState;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the logical AND of multiple conditions. May be used in a {@link ChoiceState}.
 *
 * @see <a href="https://states-language.net/spec.html#choice-state">https://states-language.net/spec.html#choice-state</a>
 * @see Choice
 */
public final class AndCondition implements NAryCondition {

    @JsonProperty(PropertyNames.AND)
    private final List<Condition> conditions;

    private AndCondition(Builder builder) {
        this.conditions = Buildable.Utils.build(builder.conditions);
    }

    /**
     * @return Builder instance to construct a {@link AndCondition}.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * @return List of conditions contained in the AND expression.
     */
    @Override
    public List<Condition> getConditions() {
        return conditions;
    }

    /**
     * Builder for a {@link AndCondition}.
     */
    public static final class Builder implements Condition.Builder {

        private final List<Condition.Builder> conditions = new ArrayList<Condition.Builder>();

        private Builder() {
        }

        /**
         * Adds a condition to the AND expression. May be another composite condition or a simple condition.
         *
         * @param conditionBuilder Instance of {@link Builder}.
         *                         Note that the
         *                         {@link Condition} object is not built until the {@link AndCondition} is built so any
         *                         modifications on the state model will be reflected in this object.
         * @return This object for method chaining.
         */
        public Builder condition(Condition.Builder conditionBuilder) {
            this.conditions.add(conditionBuilder);
            return this;
        }

        /**
         * Adds the conditions to the AND expression. May be other composite conditions or simple conditions.
         *
         * @param conditionBuilders Instances of {@link Builder}.
         *                          Note that the
         *                          {@link Condition} object is not built until the {@link AndCondition} is built so any
         *                          modifications on the state model will be reflected in this object.
         * @return This object for method chaining.
         */
        public Builder conditions(Condition.Builder... conditionBuilders) {
            for (Condition.Builder conditionBuilder : conditionBuilders) {
                condition(conditionBuilder);
            }
            return this;
        }

        /**
         * @return An immutable {@link AndCondition} object.
         */
        @Override
        public Condition build() {
            return new AndCondition(this);
        }
    }
}
