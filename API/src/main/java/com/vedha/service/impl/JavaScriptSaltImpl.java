package com.vedha.service.impl;

import com.oracle.truffle.js.scriptengine.GraalJSScriptEngine;
import com.vedha.exception.JavaScriptEncDecException;
import com.vedha.service.JavaScriptSalt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.script.Invocable;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class JavaScriptSaltImpl implements JavaScriptSalt {

    private final ResourceLoader resourceLoader;

    public static final String BITS = "256";

    @Override
    public String encrypt(String key, String value) {

        try {

            GraalJSScriptEngine graalJSScriptEngine = GraalJSScriptEngine.create(
                    Engine.newBuilder().option("engine.WarnInterpreterOnly", "false").build(),
                    Context.newBuilder("js")
            );

            /*ClassPathResource classPathResource = new ClassPathResource("/decrypt/Decryption.js");
            Reader reader = new InputStreamReader(new FileInputStream(classPathResource.getFile()));*/

            // Added Because Reading in jar file
            InputStream inputStream = resourceLoader.getResource(ResourceUtils.CLASSPATH_URL_PREFIX.concat("decrypt/Decryption.js")).getInputStream();
            Reader reader = new InputStreamReader(inputStream);
            graalJSScriptEngine.eval(reader);

            return (String) ((Invocable) graalJSScriptEngine).invokeFunction("encrypt", value, key, BITS);

        }catch (Exception e) {

            log.error("Exception in JavaScriptSaltImpl encrypt : " + e);
            throw new JavaScriptEncDecException("Encrypt Failed : " + e);
        }

    }

    @Override
    public String decrypt(String key, String value) {

        try {

            GraalJSScriptEngine graalJSScriptEngine = GraalJSScriptEngine.create(
                    Engine.newBuilder().option("engine.WarnInterpreterOnly", "false").build(),
                    Context.newBuilder("js")
            );

           /* ClassPathResource classPathResource = new ClassPathResource("/decrypt/Decryption.js");
            Reader reader = new InputStreamReader(new FileInputStream(classPathResource.getFile()));*/

            // Added Because Reading in jar file
            InputStream inputStream = resourceLoader.getResource(ResourceUtils.CLASSPATH_URL_PREFIX.concat("decrypt/Decryption.js")).getInputStream();
            Reader reader = new InputStreamReader(inputStream);
            graalJSScriptEngine.eval(reader);

            return (String) ((Invocable) graalJSScriptEngine).invokeFunction("decrypt", value, key, BITS);

        }catch (Exception e) {

            log.error("Exception in JavaScriptSaltImpl decrypt : " + e);
            throw new JavaScriptEncDecException("Decrypt Failed : " + e);
        }

    }

    @Override
    public String base64Encrypt(String value) {

        String s = Optional.ofNullable(value).orElseThrow(() -> new RuntimeException("value not found"));
//        return Base64.getEncoder().withoutPadding().encodeToString(s.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String base64Decrypt(String value) {

        String s = Optional.ofNullable(value).orElseThrow(() -> new RuntimeException("value not found"));
        byte[] decode = Base64.getDecoder().decode(s.getBytes(StandardCharsets.UTF_8));
        return new String(decode);
    }
}
