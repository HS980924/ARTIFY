package com.born.artify.domain.translation.dto;

public class InfoReqDTO {

    private final LanguageReqDTO language;
    private final ParamsReqDTO params;
    private final String template;
    private final Object template_variable;

    public InfoReqDTO(LanguageReqDTO language, ParamsReqDTO params, String template, Object template_variable) {
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

    public Object getTemplate() {
        return template;
    }

    public Object getTemplate_variable() {
        return template_variable;
    }
}
