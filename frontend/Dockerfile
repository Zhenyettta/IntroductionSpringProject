FROM node:20.3-alpine

WORKDIR /usr/src/app/frontend

COPY package.json ./
COPY tsconfig.json ./
RUN npm i

COPY . .

CMD ["npm", "run", "dev"]