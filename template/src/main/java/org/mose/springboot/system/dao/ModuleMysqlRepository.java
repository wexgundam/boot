package org.mose.springboot.system.dao;

import org.mose.springboot.dao.stream.AbstractStreamRepository;
import org.mose.springboot.system.modal.Module;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 *
 * @Author: 靳磊
 * @Date: 2017/8/14:22
 */
@Profile("dev")
@Component
public class ModuleMysqlRepository extends AbstractStreamRepository<Module, Integer> implements IModuleRepository {
    @Override
    public List<Module> queryAll() {
        String sql = "select id, name ,description, parent_id, scenario_id, icon, display_order from T_MODULE";
        return query().sql(sql).queryMany();
    }
}
