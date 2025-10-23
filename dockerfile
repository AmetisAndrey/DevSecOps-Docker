FROM openjdk:17-slim
WORKDIR /app
COPY Main.java .
RUN javac Main.java
CMD ["java", "Main"]