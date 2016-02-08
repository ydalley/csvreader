
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManagerFactory;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.jmx.impl.JMXRegistrar.IndexingProgressMonitor;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ebsl.data.model.Code;
import com.ebsl.data.model.Option;
import com.ebsl.data.model.PaymentRequest;
import com.ebsl.data.model.User;

public class Main {
	static ClassPathXmlApplicationContext ctx;

	public static void main(String[] args) throws InterruptedException {
		ctx = new ClassPathXmlApplicationContext("batchContext.xml");
		reindex();
		System.exit(0);
		/*
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					reindex();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		;
		*/

		Scanner scanner = new Scanner(System.in);
		String consoleInput = null;

		while (true) {
			// Prompt the user to enter query string
			System.out.print("\n\nEnter search key (To exit type 'X')");
			consoleInput = scanner.nextLine();

			if ("X".equalsIgnoreCase(consoleInput)) {
				System.out.println("End");
				System.exit(0);
			}

			List<User> result = search(consoleInput);
			System.out.println("\n\n>>>>>>Record found for '" + consoleInput
					+ "'");

			for (User contact : result) {
				System.out.println(contact);
			}
		}
	}

	public static List<User> search(String pattern) {
		SessionFactory sf = (SessionFactory) ctx.getBean("sessionFactory");
		Session session = sf.openSession();
		FullTextSession fSession = Search.getFullTextSession(session);

		QueryBuilder queryBuilder = fSession.getSearchFactory()
				.buildQueryBuilder().forEntity(User.class).get();
		org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().fuzzy()
				.onFields("firstName", "lastName", "email", "loginId", "phone")
				.matching(pattern).createQuery();

		// wrap Lucene query in a javax.persistence.Query
		FullTextQuery fullTextQuery = fSession.createFullTextQuery(luceneQuery,
				User.class);
		long size = fullTextQuery.getResultSize();
		List<User> list = fullTextQuery.list();
		return list;

	}

	private static void doIndex() throws InterruptedException {
		SessionFactory sf = (SessionFactory) ctx.getBean("sessionFactory");
		Session session = sf.openSession();

		FullTextSession fullTextSession = Search.getFullTextSession(session);
		fullTextSession.createIndexer().startAndWait();

		fullTextSession.close();
	}

	public static void reindex() throws InterruptedException {

		SessionFactory sf = (SessionFactory) ctx.getBean("sessionFactory");
		Session session = sf.openSession();
		IndexingProgressMonitor monitor = new IndexingProgressMonitor();
		FullTextSession textSession = Search.getFullTextSession(session);
		textSession.createIndexer(Code.class).batchSizeToLoadObjects(5)
				.cacheMode(CacheMode.NORMAL).threadsToLoadObjects(2)
				.idFetchSize(20).threadsForSubsequentFetching(3)
				.progressMonitor(monitor).startAndWait();
		textSession.createIndexer(User.class).batchSizeToLoadObjects(5)
				.cacheMode(CacheMode.NORMAL).threadsToLoadObjects(2)
				.idFetchSize(20).threadsForSubsequentFetching(3)
				.progressMonitor(monitor).startAndWait();
		textSession.createIndexer(PaymentRequest.class).batchSizeToLoadObjects(5)
		.cacheMode(CacheMode.NORMAL).threadsToLoadObjects(5)
		.idFetchSize(50).threadsForSubsequentFetching(3)
		.progressMonitor(monitor).startAndWait();
		
		textSession.createIndexer(Option.class).batchSizeToLoadObjects(5)
		.cacheMode(CacheMode.NORMAL).threadsToLoadObjects(5)
		.idFetchSize(50).threadsForSubsequentFetching(3)
		.progressMonitor(monitor).startAndWait();
	}
}