package org.mose.springboot.system.service;

import org.mose.springboot.spring.ResourceConfiguration;
import org.mose.springboot.system.dao.IModuleRepository;
import org.mose.springboot.system.modal.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        for (Module module : getModules()) {
            if (module.getParent() == null) {
                stringBuffer.append(createHeadingHtml(module));
            }
        }
        return stringBuffer.substring(0);
    }

    private String createHeadingHtml(Module module) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<li class=\"heading\">");
        stringBuffer.append("<h3 class=\"uppercase\">" + module.getName() + "</h3>");
        stringBuffer.append("</li>");
        if (module.getChildren() != null && !module.getChildren().isEmpty()) {
            for (Module child : module.getChildren()) {
                stringBuffer.append(createNodeHtml(child));
            }
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

    public List<Module> getModules() {
        List<Module> modules = new ArrayList<>();
        List<Module> allModules = moduleRepository.queryAll();
        for (Module module : allModules) {
            if (module.getParent() == null) {
                findChildren(allModules, module);
                modules.add(module);
            }
        }
        Collections.sort(modules, Comparator.comparingInt(Module::getDisplayOrder));
        return modules;
    }

    private void findChildren(List<Module> modules, Module module) {
        List<Module> children = new ArrayList<>();
        for (Module child : modules) {
            if (module.equals(child.getParent())) {
                findChildren(modules, child);
                children.add(child);
            }
        }
        Collections.sort(children, Comparator.comparingInt(Module::getDisplayOrder));
        module.setChildren(children.isEmpty() ? null : children);
    }
}
