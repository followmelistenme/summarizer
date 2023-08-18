FROM jitesoft/node-yarn as build
WORKDIR /build
COPY ./ ./
RUN cd frontend && yarn && yarn build:dev

FROM nginx as app
WORKDIR /app
COPY --from=build ./build/frontend/dist /usr/share/nginx/html
COPY --from=build ./build/frontend/ngixn/ /etc/nginx/conf.d/
ENTRYPOINT ["nginx", "-g", "daemon off;"]

