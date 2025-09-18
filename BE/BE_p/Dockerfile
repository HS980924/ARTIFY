# 베이스 이미지
FROM python:3.10.12-slim

# 작업 디렉토리
WORKDIR /app

# requirements.txt 복사 및 설치
COPY environments/requirements.txt .
RUN pip install --upgrade pip && \
    pip install -r requirements.txt

# 소스 전체 복사
COPY . .

# uvicorn 실행 (Render에서 제공하는 PORT 환경변수 사용)
CMD ["sh", "-c", "uvicorn main:app --host 0.0.0.0 --port $PORT"]
