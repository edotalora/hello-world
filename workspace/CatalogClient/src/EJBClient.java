import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Producto;
import com.services.ProductServiceRemote;

public class EJBClient {
	public static void main(String[] args) {		
		try {
			Properties p = new Properties();
			p.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			p.put("jboss.naming.client.ejb.context", true);

			Context ctx=new InitialContext(p);
			ProductServiceRemote remoteService=(ProductServiceRemote) ctx.lookup("ejb:/CatalogoProject/ProductServiceBean!com.services.ProductServiceRemote");
			Producto prod= new Producto();
			prod.setName("Raquetas");
			prod.setPrice(50000);
			
			remoteService.addProducto(prod);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

