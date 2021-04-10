package com.bluecc.packer.pipes;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.mvel.MVELRule;

public class PipesWaver {
    public static Rule createRuleFrom(RuleDef ruleDef){
        MVELRule rule= new MVELRule()
                .name(ruleDef.getName())
                .description(ruleDef.getDescription())
                .priority(ruleDef.getPriority())
                .when(ruleDef.getCondition());
        for(String act:ruleDef.getActionsList()){
            rule.then(act);
        }
        return rule;
    }
}

