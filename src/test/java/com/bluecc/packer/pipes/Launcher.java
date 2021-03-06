/*
 * The MIT License
 *
 *  Copyright (c) 2020, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package com.bluecc.packer.pipes;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRule;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.reader.YamlRuleDefinitionReader;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class Launcher {
    public static URL getAlcoholRuleYamlFile() {
        return JsonReaderTest.class.getResource("alcohol-rule.yml");
    }

    public static void main(String[] args) throws Exception {
        //create a person instance (fact)
        Person tom = new Person("Tom", 14);
        Facts facts = new Facts();
        facts.put("person", tom);

        // create rules
        MVELRule ageRule = new MVELRule()
                .name("age rule")
                .description("Check if person's age is > 18 and mark the person as adult")
                .priority(1)
                .when("person.age > 18")
                .then("person.setAdult(true);");

        MVELRuleFactory ruleFactory = new MVELRuleFactory(new YamlRuleDefinitionReader());
        URL file=getAlcoholRuleYamlFile();
        InputStream input = file.openStream();
        Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
        Rule alcoholRule = ruleFactory.createRule(reader);

        // create a rule set
        Rules rules = new Rules();
        rules.register(ageRule);
        rules.register(alcoholRule);

        //create a default rules engine and fire rules on known facts
        RulesEngine rulesEngine = new DefaultRulesEngine();

        System.out.println("Tom: Hi! can I have some Vodka please?");
        rulesEngine.fire(rules, facts);
    }

}


