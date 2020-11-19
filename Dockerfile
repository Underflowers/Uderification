FROM openjdk:11-slim
RUN addgroup --system spring && adduser --system --ingroup spring spring
USER spring:spring
ARG DEPENDENCY
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","io.underflowers.underification.Swagger2SpringBoot"]