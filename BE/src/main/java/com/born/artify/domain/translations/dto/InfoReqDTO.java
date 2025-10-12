package com.born.artify.domain.translations.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class InfoReqDTO {

    private final LanguageReqDTO language;
    private final ParamsReqDTO params;
    private final String template;
    private Map<String, String> template_variable = new HashMap<>();

    public InfoReqDTO(LanguageReqDTO language, ParamsReqDTO params, String template, Map<String, String>  template_variable) {
        this.language = language;
        this.params = params;
        this.template = template;
        this.template_variable = template_variable;
    }

    public LanguageReqDTO getLanguage() {
        return language;
    }

    public ParamsReqDTO getParams() {
        return params;
    }

    public String getTemplate() {
        return template;
    }

    public Map<String, String> getTemplate_variable() {
        return template_variable;
    }

    public void addTemplateVariable(String key, String value) {
        template_variable.put(key, value);
    }
}
