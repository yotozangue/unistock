DOCKER_COMPOSE = docker-compose
DOCKER_IMAGE_NAME = unistock
MAVEN_CMD = mvn clean package
WAR_FILE = target/unistock.war

all: build docker

build:
	@echo "Compiling the project with Maven..."
	$(MAVEN_CMD)

docker:
	@echo "Building the Docker image with name $(DOCKER_IMAGE_NAME) and running containers..."
	docker-compose up --build

run-docker:
	@echo "Running the Docker container $(DOCKER_IMAGE_NAME)..."
	docker run -p 8080:8080 $(DOCKER_IMAGE_NAME)

stop:
	@echo "Stopping the containers..."
	docker-compose down

clean:
	@echo "Cleaning build files..."
	$(MAVEN_CMD) clean
	rm -rf $(WAR_FILE)

rebuild: clean build docker

help:
	@echo "Available commands:"
	@echo "  make all          - Compile the project and run Docker"
	@echo "  make build        - Compile the project with Maven"
	@echo "  make docker       - Build and run Docker containers"
	@echo "  make run-docker   - Run the Docker container manually"
	@echo "  make stop         - Stop the containers"
	@echo "  make clean        - Clean build files and target folder"
	@echo "  make rebuild      - Clean, compile, and run Docker"
	@echo "  make help         - Show this help message"
