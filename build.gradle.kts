plugins {
	java
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.bannrx"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
	val user: String? = project.findProperty("username") as String? ?: System.getenv("GITHUB_USERNAME")
	val token: String? = project.findProperty("token") as String? ?: System.getenv("GITHUB_TOKEN")
	val repo = "utility"
	val gitUrl = "https://maven.pkg.github.com/${user}/${repo}"
	maven {
		name = "GitHubPackages"
		url = uri(gitUrl)
		credentials {
			username = user
			password = token
		}
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.mysql:mysql-connector-j")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	//added
	implementation("com.rklab:utility:0.0.1-SNAPSHOT")
	implementation("org.springframework.boot:spring-boot-starter-security:3.1.0")
	implementation("org.springframework.security:spring-security-config:6.0.0")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
