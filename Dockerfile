ARG APP_DIR=/app
ARG DEPENDENCY_DIR=$APP_DIR/target/dependency

FROM maven:3-openjdk-11-slim AS builder

ARG IMPLEMENTATION_DIR=./api-impl
ARG APP_DIR
ARG DEPENDENCY_DIR

COPY $IMPLEMENTATION_DIR $APP_DIR

# Building application
RUN cd $APP_DIR && mvn package
RUN mkdir -p $DEPENDENCY_DIR && (cd $DEPENDENCY_DIR; jar -xf ../*.jar)

FROM openjdk:11-slim

# Right management
RUN addgroup --system spring && adduser --system --ingroup spring spring
USER spring:spring

ARG APP_DIR
ARG DEPENDENCY_DIR

COPY --from=builder --chown=spring:spring ${DEPENDENCY_DIR}/BOOT-INF/lib $APP_DIR/lib
COPY --from=builder --chown=spring:spring ${DEPENDENCY_DIR}/META-INF $APP_DIR/META-INF
COPY --from=builder --chown=spring:spring ${DEPENDENCY_DIR}/BOOT-INF/classes $APP_DIR

ENTRYPOINT ["java","-cp","app:app/lib/*","io.underflowers.underification.Swagger2SpringBoot"]