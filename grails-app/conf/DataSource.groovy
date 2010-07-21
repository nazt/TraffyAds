dataSource {
	pooled = true
	driverClassName = "org.hsqldb.jdbcDriver"
	username = "sa"
	password = ""
}
hibernate {
    cache.use_second_level_cache=true
    cache.use_query_cache=true
    cache.provider_class='net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
	development {
		dataSource {
			dbCreate = "create-drop" // one of 'create', 'create-drop','update'
			url = "jdbc:hsqldb:mem:devDB"
		}
	}
	test {
	  dataSource {
	   dbCreate = "update"
	   url="jdbc:postgresql://localhost:5432/test"
	   driverClassName = "org.postgresql.Driver"
	                        username = "tester"
	                        password = "tester"
	  }
	}
	production {
	  dataSource {
	   dbCreate = "update"
	   url="jdbc:postgresql://203.185.97.51:5432/natz"
	   driverClassName = "org.postgresql.Driver"
	                        username = "natz"
	                        password = "natz2traffy"
	  }
	}
}