package com.pojo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class HibernateMain {

	public static void main(String[] args) {

		//load the config xml file into the java

		//SSR is used to map the config file and execute it
		StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		//meta data in the config file need to be executed
		Metadata md=new MetadataSources(ssr).getMetadataBuilder().build();
		//go to the taag sessionfactory and execute the metadata in it
		SessionFactory sf=md.getSessionFactoryBuilder().build();
		//CRUD operations
		Session s=sf.openSession();
		//Transaction-start ur crud operations
		Transaction t=s.beginTransaction();
//		Random r=new Random();
//		for(int i=3;i<10;i++) {
//			Employee e=new Employee();	
//			e.setEmpname("name"+i);
//			e.setEmpemail("name"+r.nextInt(40)+"@c.c");
//			s.save(e);
//		}
		
//HQL
		//select * from employee
//		Query q=s.createQuery("from Employee");
//		List<Employee>	el=	q.list();
//		System.out.println(el);
		
		//select * from employee where empno>4
//		Query q=s.createQuery("from Employee where empno>4");
//		List<Employee>	el=	q.list();
//		System.out.println(el);

		//select * from employee where empno=4
//		Query q=s.createQuery("from Employee where empno=4");
//		Employee el=(Employee) q.uniqueResult();
//		System.out.println(el);
		
		//select empname,empemail from the employee where empno=4
//		Query q=s.createQuery("select empname,empemail from Employee where empno=4");
//		Object[] emp=(Object[]) q.uniqueResult();
//		System.out.println(emp[0]+"  "+emp[1]);
		
		//select empname,empemail from the employee where empno>4
//		Query q=s.createQuery("select empname,empemail from Employee where empno>4");
//		List<Object[]> emp=q.list();
//		for(Object[] e:emp) {
//			System.out.println(e[0]+"  "+e[1]);
//		}
		
		//select max(empno) from employee
//		Query q=s.createQuery("select max(empno) from Employee");
//		int e=(int) q.uniqueResult();
//		System.out.println(e);
		
		//select empname from  employee where empno=4
//		Query q=s.createQuery("select empname from  Employee where empno=4");
//		String e=(String) q.uniqueResult();
//		System.out.println(e);
	
		//update or delete
//		Query q=s.createQuery("delete from Employee where empno=:no");
//		q.setParameter("no", 2);
//		int i=q.executeUpdate();
//		System.out.println(i);

		
		
		//inserted into the pojo data into the table
		//s.save(e);
		//s.persist(e);
		//fetch the details
		//Employee e1=s.get(Employee.class,2);
		//Employee e1=s.load(Employee.class,2);
		//System.out.println(e1);
		//update the object
		//s.saveOrUpdate(e);
		//delete of the object
		//s.delete(e);
		//to commit the opeartion -to have a permanent save on the table
		t.commit();
		s.close();
		sf.close();


	}
}



