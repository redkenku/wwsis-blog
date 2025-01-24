package pl.wwsis.zpp.blog;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerRoute;

public class App {
	public static void main(String[] args) {
		get(new FreeMarkerRoute("/hello") {
			@Override
			public Object handle(Request request, Response response) {
				Map<String, Object> attributes = new HashMap<String, Object>();
				attributes.put("message", "Hello, World!");
				return modelAndView(attributes, "hello.ftl");
			}
		});
	}
}
