package org.mose.springboot.sidebar.jdbcrepository;

import java.util.Map;

public interface RowUnmapper<T> {
	Map<String, Object> mapColumns(T t);
}

