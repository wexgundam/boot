package org.mose.springboot.system.service;

import org.mose.springboot.spring.ResourceConfiguration;
import org.mose.springboot.system.dao.IModuleRepository;
import org.mose.springboot.system.modal.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/15.
 */
@Service
public class SidebarService {
    @Autowired
    private IModuleRepository moduleRepository;
    @Autowired
    ResourceConfiguration resourceConfiguration;


    public String getSidebarHtml() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Module module : getSidebarItems()) {
            stringBuffer.append(createNodeHtml(module));
        }
        return stringBuffer.substring(0);
    }

    private String createNodeHtml(Module module) {
        if (module.getChildren() == null || module.getChildren().isEmpty()) {
            return createLeafNodeHtml(module);
        } else {
            return createBranchNodeHtml(module);
        }
    }

    private String createBranchNodeHtml(Module module) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<li class=\"nav-item\">");
        stringBuffer.append("<a href=\"javascript:;\" class=\"nav-link nav-toggle\">");
        stringBuffer.append("<i class=\"" + module.getIcon() + "\"></i>");
        stringBuffer.append("<span class=\"title\">" + module.getName() + "</span>");
        stringBuffer.append("<span class=\"arrow\"></span>");
        stringBuffer.append("</a>");
        stringBuffer.append("<ul class=\"sub-menu\">");
        for (Module child : module.getChildren()) {
            stringBuffer.append(createNodeHtml(child));
        }
        stringBuffer.append("</ul>");
        stringBuffer.append("</li>");

        return stringBuffer.substring(0);
    }

    private String createLeafNodeHtml(Module module) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<li class=\"nav-item\">");
        stringBuffer.append(" <a href=\"" + resourceConfiguration.getDynamicResourceServerUrl() + "/system/module/index.htm\" class=\"nav-link \">");
        stringBuffer.append("<i class=\"" + module.getIcon() + "\"></i>");
        stringBuffer.append("<span class=\"title\">" + module.getName() + "</span>");
        stringBuffer.append("</a>");
        stringBuffer.append("</li>");
        return stringBuffer.substring(0);
    }

    public List<Module> getSidebarItems() {
        List<Module> sidebar = new ArrayList<>();
        List<Module> modules = moduleRepository.queryAll();
        for (Module module : modules) {
            if (module.getParent() == null) {
                findChildren(modules, module);
                sidebar.add(module);
            }
        }
        return sidebar;
    }

    private void findChildren(List<Module> modules, Module module) {
        List<Module> children = new ArrayList<>();
        for (Module child : modules) {
            if (module.equals(child.getParent())) {
                findChildren(modules, child);
                children.add(child);
            }
        }
        module.setChildren(children.isEmpty() ? null : children);
    }
}
