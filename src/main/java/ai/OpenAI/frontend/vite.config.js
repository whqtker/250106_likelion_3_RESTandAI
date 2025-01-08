import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  build: {
    outDir: '../backend/src/main/resources/static', // 빌드 출력 디렉토리
  },
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8040', // Spring Boot 서버 주소
        changeOrigin: true
      }
    }
  }
})