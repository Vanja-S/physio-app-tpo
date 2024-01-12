#!/bin/bash

FIZIO_APP_DIR="fizio-app"
DEPLOYMENT_DIR="deployment"
SERVER_DIR="$DEPLOYMENT_DIR/server"

echo "Running 'mvn clean install -DskipTests' in $FIZIO_APP_DIR directory..."
cd "$FIZIO_APP_DIR" || exit 1
mvn clean install -DskipTests || exit 1
cd ..

if [ ! -d "$SERVER_DIR" ]; then
  echo "Creating $SERVER_DIR directory..."
  mkdir -p "$SERVER_DIR" || exit 1
fi

echo "Moving docker directory to $SERVER_DIR..."
cp -r "$FIZIO_APP_DIR/docker/" "$SERVER_DIR" || exit 1


echo "Moving $FIZIO_APP_DIR/target/fizio-app.jar to $SERVER_DIR..."
cp "$FIZIO_APP_DIR/target/fizio-app.jar" "$SERVER_DIR" || exit 1

echo "Script completed successfully."
