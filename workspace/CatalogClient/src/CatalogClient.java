import javax.naming.Context;
import javax.naming.NamingException;

import com.entities.Producto;
import com.services.ProductServiceRemote;

public class CatalogClient {
	public static void main(String[] args) throws NamingException
	 {
	  final Context context = new JBossClientContextBuilder().build();

	  String lookupString = "ejb:/CatalogoProject/ProductServiceBean!com.services.ProductServiceRemote";
	  final  ProductServiceRemote remoteService= (ProductServiceRemote) context.lookup(lookupString);
	  Producto prod= new Producto();
	  prod.setName("Raquetas");
	  prod.setPrice(50000);
		
	  remoteService.addProducto(prod);
	 }
}
