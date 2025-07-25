# Saloon Appointment Booking Application

This is a full-stack web application for booking saloon appointments. The application is built with a microservices architecture for the backend and a modern React frontend.

## Table of Contents

- [Project Overview](#project-overview)
- [Tech Stack](#tech-stack)
  - [Frontend](#frontend)
  - [Backend](#backend)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Backend Setup](#backend-setup)
  - [Frontend Setup](#frontend-setup)
- [Microservices](#microservices)

## Project Overview

The Saloon Appointment Booking Application allows users to browse saloons, view available services, and book appointments. The backend is designed with a microservices architecture to ensure scalability and maintainability.

## Tech Stack

### Frontend

- **React:** A JavaScript library for building user interfaces.
- **Vite:** A fast build tool for modern web projects.
- **Material-UI:** A popular React UI framework.
- **Tailwind CSS:** A utility-first CSS framework.
- **React Router:** For routing and navigation.

### Backend

- **Java:** The primary programming language for the backend services.
- **Spring Boot:** A framework for creating stand-alone, production-grade Spring-based applications.
- **Spring Cloud:** For building distributed systems, including service discovery (Eureka) and an API gateway.
- **Maven:** A build automation and dependency management tool.
- **Docker:** For containerizing and running the application and its services.

## Project Structure

The project is organized into two main directories:

- `SaloonAppointmentAppfrontend/`: Contains the React frontend application.
- `backend/`: Contains the backend microservices.

## Getting Started

Follow these instructions to get the project up and running on your local machine.

### Prerequisites

- **Node.js and npm:** For running the frontend application.
- **Java Development Kit (JDK):** For running the backend services.
- **Maven:** For building the backend services.
- **Docker and Docker Compose:** For running the microservices.

### Backend Setup

1.  **Navigate to the `docker-compose` directory:**

    ```bash
    cd backend/Microservices/docker-compose
    ```

2.  **Start the services using Docker Compose:**

    ```bash
    docker-compose up -d
    ```

    This will start all the backend microservices, including the Eureka server for service discovery and the API gateway.

### Frontend Setup

1.  **Navigate to the frontend directory:**

    ```bash
    cd SaloonAppointmentAppfrontend
    ```

2.  **Install the dependencies:**

    ```bash
    npm install
    ```

3.  **Start the development server:**

    ```bash
    npm run dev
    ```

    The frontend application will be available at `http://localhost:5173` (or another port if specified).

## Microservices

The backend consists of the following microservices:

- **Eureka Server:** Handles service registration and discovery, allowing microservices to find and communicate with each other dynamically.

- **Gateway Server:** The single entry point for all client requests. It routes requests to the appropriate microservices and handles cross-cutting concerns like authentication and logging.

- **Booking Service:** Manages the core appointment booking functionality. It handles creating, retrieving, and updating bookings, and communicates with other services for payment and user details.

- **Category Service:** Manages service categories. Saloon owners can create and delete categories for their saloons, while customers can browse and search for services by category.

- **Notification Service:** Handles sending notifications to users and saloon owners. It can be used for appointment reminders, booking confirmations, and other important updates.

- **Payment Service:** Processes payments for bookings. It integrates with payment gateways like Razorpay and Stripe to create and manage payment orders.

- **Review Service:** Manages user reviews and ratings for saloons. It allows users to create, retrieve, update, and delete reviews.

- **Saloon User Details:** Manages user authentication and profiles. It handles user registration, login, and token management, as well as CRUD operations for user data.

- **Saloon Service Appointment:** Manages saloon profiles and services. It allows saloon owners to create and update their saloon details, and customers to browse and search for saloons.

- **Service Offering:** Manages the specific services offered by saloons. Saloon owners can create, update, and delete service offerings, which can then be booked by customers.
