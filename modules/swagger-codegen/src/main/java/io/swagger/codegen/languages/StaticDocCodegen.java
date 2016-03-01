package io.swagger.codegen.languages;

import io.swagger.codegen.CliOption;
import io.swagger.codegen.CodegenConfig;
import io.swagger.codegen.CodegenConstants;
import io.swagger.codegen.CodegenType;
import io.swagger.codegen.DefaultCodegen;
import io.swagger.codegen.SupportingFile;

import java.io.File;

public class StaticDocCodegen extends DefaultCodegen implements CodegenConfig {
    protected String invokerPackage = "io.swagger.client";
    protected String groupId = "io.swagger";
    protected String artifactId = "swagger-client";
    protected String artifactVersion = "1.0.0";
    protected String sourceFolder = "docs";

    public StaticDocCodegen() {
        super();
        outputFolder = "docs";
        modelTemplateFiles.put("model.mustache", ".html");
        apiTemplateFiles.put("operation.mustache", ".html");
        embeddedTemplateDir = templateDir = "swagger-static";

        cliOptions.add(new CliOption(CodegenConstants.INVOKER_PACKAGE, CodegenConstants.INVOKER_PACKAGE_DESC));
        cliOptions.add(new CliOption(CodegenConstants.GROUP_ID, CodegenConstants.GROUP_ID_DESC));
        cliOptions.add(new CliOption(CodegenConstants.ARTIFACT_ID, CodegenConstants.ARTIFACT_ID_DESC));
        cliOptions.add(new CliOption(CodegenConstants.ARTIFACT_VERSION, CodegenConstants.ARTIFACT_VERSION_DESC));

        additionalProperties.put(CodegenConstants.INVOKER_PACKAGE, invokerPackage);
        additionalProperties.put(CodegenConstants.GROUP_ID, groupId);
        additionalProperties.put(CodegenConstants.ARTIFACT_ID, artifactId);
        additionalProperties.put(CodegenConstants.ARTIFACT_VERSION, artifactVersion);

        supportingFiles.add(new SupportingFile("package.mustache", "", "package.json"));
        supportingFiles.add(new SupportingFile("main.mustache", "", "main.js"));
        supportingFiles.add(new SupportingFile("assets/css/bootstrap-theme.min.css",
                outputFolder + "/assets/css", "bootstrap-theme.min.css"));
        supportingFiles.add(new SupportingFile("assets/css/bootstrap.min.css",
                outputFolder + "/assets/css", "bootstrap.min.css"));
        supportingFiles.add(new SupportingFile("assets/css/style.css",
                outputFolder + "/assets/css", "style.css"));
        supportingFiles.add(new SupportingFile("assets/css/github.css",
                outputFolder + "/assets/css", "github.css"));
        supportingFiles.add(new SupportingFile("assets/fonts/glyphicons-halflings-regular.eot",
                outputFolder + "/assets/fonts", "glyphicons-halflings-regular.eot"));
        supportingFiles.add(new SupportingFile("assets/fonts/glyphicons-halflings-regular.svg",
                outputFolder + "/assets/fonts", "glyphicons-halflings-regular.svg"));
        supportingFiles.add(new SupportingFile("assets/fonts/glyphicons-halflings-regular.ttf",
                outputFolder + "/assets/fonts", "glyphicons-halflings-regular.ttf"));
        supportingFiles.add(new SupportingFile("assets/fonts/glyphicons-halflings-regular.woff",
                outputFolder + "/assets/fonts", "glyphicons-halflings-regular.woff"));
        supportingFiles.add(new SupportingFile("assets/fonts/glyphicons-halflings-regular.woff2",
                outputFolder + "/assets/fonts", "glyphicons-halflings-regular.woff2"));
        supportingFiles.add(new SupportingFile("assets/images/logo.png",
                outputFolder + "/assets/images", "logo.png"));
        supportingFiles.add(new SupportingFile("assets/js/bootstrap.min.js",
                outputFolder + "/assets/js", "bootstrap.min.js"));
        supportingFiles.add(new SupportingFile("assets/js/marked.min.js",
                outputFolder + "/assets/js", "marked.min.js"));
        supportingFiles.add(new SupportingFile("assets/js/jquery-2.2.0.min.js",
                outputFolder + "/assets/js", "jquery-2.2.0.min.js"));
        supportingFiles.add(new SupportingFile("assets/js/main.js",
                outputFolder + "/assets/js", "main.js"));
        supportingFiles.add(new SupportingFile("assets/js/highlight.pack.js",
                outputFolder + "/assets/js", "highlight.pack.js"));
        supportingFiles.add(new SupportingFile("index.mustache",
                outputFolder, "index.html"));
        supportingFiles.add(new SupportingFile("intro.mustache",
                outputFolder, "intro.html"));
        supportingFiles.add(new SupportingFile("guide.html",
                outputFolder, "guide.html"));

        instantiationTypes.put("array", "ArrayList");
        instantiationTypes.put("map", "HashMap");
    }

    @Override
    public CodegenType getTag() {
        return CodegenType.DOCUMENTATION;
    }

    @Override
    public String getName() {
        return "dynamic-html";
    }

    @Override
    public String getHelp() {
        return "Generates a dynamic HTML site.";
    }

    @Override
    public String escapeReservedWord(String name) {
        return "_" + name;
    }

    @Override
    public String apiFileFolder() {
        return outputFolder + File.separator + sourceFolder + File.separator + "operations";
    }

    @Override
    public String modelFileFolder() {
        return outputFolder + File.separator + sourceFolder + File.separator + "models";
    }
}