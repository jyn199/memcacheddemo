package memcacheddemo;

import javax.annotation.Resource;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:applicationSpringApp.xml", "classpath:application-memcached.xml" })
public class SpyMemcachedTest extends AbstractJUnit4SpringContextTests {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource  
    protected ApplicationContext applicationContext;
	
	@Test
	public void testMemcached() {
		try {
			System.out.println(applicationContext + "===");
			MemcachedClient client = applicationContext.getBean(MemcachedClient.class);
			// Store a value (async) for one hour
			client.set("someKey", 3600, "中文值!");
			// replace the value
			client.replace("someKey", 33, "另一个值!");
			// Retrieve a value.
			Object ret = client.get("someKey");
			Object name = client.get("name");
			System.out.println(ret + "===" + name);
			OperationFuture<Boolean> retb = client.delete("someKey");
			logger.debug("{}:{}",ret,retb.get());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("test");
			logger.error("", e);
		}
	}

}
