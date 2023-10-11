package com.naukma.introductionspringproject.config;

import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;
import org.apache.logging.log4j.util.Strings;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
@Plugin(name = "CustomLayout", category = Core.CATEGORY_NAME, elementType = Layout.ELEMENT_TYPE, printObject = true)
public class Layout extends AbstractStringLayout {

    protected Layout(Charset charset) {
        super(charset, null, null);
    }

    @Override
    public String toSerializable(LogEvent event) {
        return String.format("Timestamp: %s, Logger: %s, Level: %s, Message: %s%n",
                Instant.ofEpochMilli(event.getTimeMillis()),
                event.getLoggerName(),
                event.getLevel(),
                event.getMessage().getFormattedMessage());
    }

    @Override
    public byte[] getHeader() {
        return Strings.EMPTY.getBytes();
    }

    @Override
    public byte[] getFooter() {
        return Strings.EMPTY.getBytes();
    }

    @PluginFactory
    public static Layout createLayout() {
        return new Layout(StandardCharsets.UTF_8);
    }

}
