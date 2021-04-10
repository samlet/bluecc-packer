package com.bluecc.packer.pipes;

import com.google.protobuf.util.JsonFormat;
import io.grpc.examples.routeguide.RouteGuideServer;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class JsonReaderTest {
    public static URL getAdultRuleFile() {
        return JsonReaderTest.class.getResource("adult-rule-adapter.json");
    }

    @Test
    public void testJsonResource() throws IOException {
        URL file=getAdultRuleFile();
        InputStream input = file.openStream();
        Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
//        String cnt =  new BufferedReader(reader).lines().collect(Collectors.joining());
//        System.out.println(cnt);

        RuleDefs.Builder rules=RuleDefs.newBuilder();
        JsonFormat.parser().merge(reader, rules);
        RuleDef rule=rules.getRuleDef(0);
        System.out.println(rule);
    }

}
