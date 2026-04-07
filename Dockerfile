# syntax=docker/dockerfile:1
# Build: docker build -t elite-trader-backend:latest .
# Run:  docker run --rm -p 8080:8080 elite-trader-backend:latest

FROM eclipse-temurin:17-jdk-jammy AS builder
WORKDIR /app

COPY gradlew settings.gradle.kts build.gradle.kts ./
COPY gradle gradle
RUN chmod +x gradlew

COPY src src

RUN ./gradlew bootJar --no-daemon -x test \
 && JAR="$(ls build/libs/*.jar | grep -v '\-plain\.jar$' | head -n1)" \
 && test -f "$JAR" \
 && cp "$JAR" /app/application.jar

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

RUN apt-get update \
 && DEBIAN_FRONTEND=noninteractive apt-get install -y --no-install-recommends curl \
 && rm -rf /var/lib/apt/lists/* \
 && groupadd --system spring \
 && useradd --system --gid spring --home /app --shell /usr/sbin/nologin spring \
 && chown -R spring:spring /app

COPY --from=builder --chown=spring:spring /app/application.jar app.jar

USER spring:spring

EXPOSE 8080

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75"

HEALTHCHECK --interval=30s --timeout=5s --start-period=50s --retries=3 \
  CMD curl -sf http://127.0.0.1:8080/api/verify/health | grep -q '"status"' || exit 1

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar /app/app.jar"]
