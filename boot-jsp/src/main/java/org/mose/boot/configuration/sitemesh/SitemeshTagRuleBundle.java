package org.mose.boot.configuration.sitemesh;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

/**
 * what:    Sitemesh标签定义规则
 */
public class SitemeshTagRuleBundle implements TagRuleBundle {
    /**
     * what:    添加自定义标签处理器
     *
     * @param defaultState
     * @param contentProperty
     * @param siteMeshContext
     */
    @Override
    public void install(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
        defaultState.addRule("content-css", new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("content-css"), false));
        defaultState.addRule("content-script", new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("content-script"), false));

        defaultState.addRule("sidebar-css", new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("sidebar-css"), false));
        defaultState.addRule("sidebar-script", new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("sidebar-script"), false));
    }

    @Override
    public void cleanUp(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
    }
}
