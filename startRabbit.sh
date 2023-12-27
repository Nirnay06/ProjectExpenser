#!/bin/bash
osascript -e 'tell app "Terminal"
    do script "redis-server"
end tell'
CONF_ENV_FILE="/opt/homebrew/etc/rabbitmq/rabbitmq-env.conf" /opt/homebrew/opt/rabbitmq/sbin/rabbitmq-server