import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.ejb.client.ContextSelector;
import org.jboss.ejb.client.EJBClientConfiguration;
import org.jboss.ejb.client.EJBClientContext;
import org.jboss.ejb.client.PropertiesBasedEJBClientConfiguration;
import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;

public class JBossClientContextBuilder {
 private String user;
 private String password;
 private boolean noanonymous;

 public JBossClientContextBuilder user(String user) {
  this.user = user;
  return this;
 }

 public JBossClientContextBuilder password(String password) {
  this.password = password;
  return this;
 }

 public JBossClientContextBuilder noanonymous(boolean noanonymous) {
  this.noanonymous = noanonymous;
  return this;
 }

 public Context build() throws NamingException {
  Properties clientProp = new Properties();
  clientProp
    .put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED",
      "false");
  clientProp.put("remote.connections", "default");

  // Default 4447
  clientProp.put("remote.connection.default.port", "4447");

  // Default 127.0.0.1
  clientProp.put("remote.connection.default.host","127.0.0.1");

//  if (!StringUtils.isEmpty(user)) {
//   clientProp.put("remote.connection.default.username", user);
//  }
//  if (!StringUtils.isEmpty(password)) {
//   clientProp.put("remote.connection.default.password", password);
//  }

  if (noanonymous) {
   clientProp
     .put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS",
       "true");
   clientProp
     .put("remote.connection.default.connect.options.org.xnio.Options.SASL_DISALLOWED_MECHANISMS",
       "JBOSS-LOCAL-USER");
  }
  clientProp
    .put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT",
      "false");

  System.out.println(Arrays.deepToString(clientProp.entrySet().toArray(
    new Map.Entry[0])));

  EJBClientConfiguration cc = new PropertiesBasedEJBClientConfiguration(
    clientProp);
  ContextSelector<EJBClientContext> selector = new ConfigBasedEJBClientContextSelector(
    cc);
  EJBClientContext.setSelector(selector);

  Properties props = new Properties();
  props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

  return new InitialContext(props);
 }
}