#!/bin/bash
set -a
source .env
set +a

SERVICE=${1:-billing-service}
echo "Starting $SERVICE with environment variables..."
cd "$SERVICE" && gradle bootRun
