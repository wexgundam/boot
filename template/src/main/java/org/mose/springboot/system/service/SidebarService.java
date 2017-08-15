package org.mose.springboot.system.service;

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

    public List<Module> getSidebarItems() {
        List<Module> sidebar = new ArrayList<>();
        List<Module> modules = moduleRepository.queryAll();
        Map<String, List<Module>> subModules = new HashMap<>();
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
