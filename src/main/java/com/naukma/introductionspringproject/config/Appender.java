package com.naukma.introductionspringproject.config;

import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

@Plugin(name = "CustomFileAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE, printObject = true)
public class Appender extends AbstractAppender {
    private final String fileName;

    protected Appender(String name, Filter filter, Layout layout, boolean ignoreExceptions, String fileName) {
        super(name, filter, layout, ignoreExceptions, Property.EMPTY_ARRAY);
        this.fileName = Objects.requireNonNull(fileName);
    }

    @PluginFactory
    public static Appender createAppender(
            @PluginAttribute("name") String name,
            @PluginAttribute(value = "fileName") String fileName) {
        return new Appender(name, null, Layout.createLayout(), false, fileName);
    }

    @Override
    public void append(LogEvent event) {
        try {
            final byte[] bytes = this.getLayout().toByteArray(event);
            Files.write(Paths.get(this.fileName), bytes, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException ioe) {
            System.err.println("Could not write log to file " + this.fileName);
        }
    }


}