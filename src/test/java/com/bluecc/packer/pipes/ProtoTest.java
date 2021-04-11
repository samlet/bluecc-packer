package com.bluecc.packer.pipes;

import com.bluecc.packer.routines.PipeResponse;
import org.junit.Test;

public class ProtoTest {
    @Test
    public void testOneOf(){
        PipeResponse.Builder resp=PipeResponse.newBuilder();
        resp.setTag("test").setIntValue(18);
        PipeResponse r=resp.build();
        System.out.println(r+", get string-value: "+r.getStringValue());
    }
}
