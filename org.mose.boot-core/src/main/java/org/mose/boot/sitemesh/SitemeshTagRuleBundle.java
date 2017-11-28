package org.mose.boot.sitemesh;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

/**
 *
 */
public class SitemeshTagRuleBundle implements TagRuleBundle {
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
