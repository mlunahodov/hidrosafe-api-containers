#!/bin/bash

echo "🚀 Limpando containers antigos..."
docker-compose down

echo "📦 Fazendo build da imagem e subindo os containers..."
docker-compose up --build -d

echo "⏳ Aguardando containers iniciarem..."
sleep 10

echo "📄 Exibindo logs da aplicação:"
docker-compose logs -f app
