# Week 1-2: Core Setup and Content Organization for VelvetFlow CMS

---

## Week 1: Core Setup

### Learning Tasks
1. **KMM Fundamentals and Project Structure**
    - Learn how Kotlin Multiplatform Mobile (KMM) works for cross-platform apps.
    - Research best practices for project module structure and naming conventions.

2. **Ktor Framework Basics**
    - Understand API routes, controllers, and basic configuration for the Ktor backend.
    - Learn how to configure app metadata to reflect the VelvetFlow CMS project name.

3. **AWS Free Tier Configurations**
    - Study AWS Free Tier services and limitations.
    - Research naming conventions for AWS resources, such as S3 buckets and IAM roles.

4. **PostgreSQL Database Fundamentals**
    - Study database schema design for PostgreSQL and how to configure project-specific identifiers.

---

### Development Tasks (3-hour Sunday block)

#### Hour 1: Core Development
1. **Set Up KMM Project Structure**
    - Create a new KMM project.
    - Rename modules to:
        - `velvetflow-shared` (shared business logic).
        - `velvetflow-android` and `velvetflow-ios` (platform-specific logic).
    - Update the project namespace to reflect VelvetFlow:
        - Namespace: `com.velvetflow.cms`.

2. **Configure Ktor Backend**
    - Initialize a simple Ktor app with a health-check route (`/health`).
    - Set the application name in `application.conf` or within the configuration to `VelvetFlowCMS`.

3. **Initialize PostgreSQL Database Schema**
    - Create a project-named database: `velvetflow_cms_db`.
    - Set up tables:
        - `Users` (id, email, password, created_at).
        - `Files` (id, file_path, file_type, tags, created_at).

---

#### Hour 2: AWS Setup
1. **Configure AWS S3 Storage Bucket**
    - S3 bucket name: `velvetflow-cms-storage`.
    - Set up folder structure:
        - `/uploads/{userId}/{fileType}/filename`.
    - Configure IAM role and environment variables:
        - IAM Role: `velvetflow-cms-s3-role`.
        - Environment variable: `S3_BUCKET_NAME=velvetflow-cms-storage`.

2. **Set Up AWS Free Tier Resources**
    - Ensure AWS Free Tier services align with the VelvetFlow CMS needs.
    - Add resource-specific permissions for:
        - S3 bucket access.
        - CloudWatch (monitoring).

3. **Configure Cloudflare CDN**
    - Add a domain such as `cms.velvetflow.com` or Cloudflare subdomain (`velvetflow-cms.cloudflare.dev`).
    - Configure caching rules and routing for static files and APIs.

---

#### Hour 3: Documentation
1. **Document Project Structure**
    - Add details in `PROJECT_STRUCTURE.md`, reflecting VelvetFlow CMS:
      ```markdown
      ## VelvetFlow CMS Project Structure
      - `velvetflow-shared`: Contains business logic and shared functionality.
      - `velvetflow-android`: Platform-specific code for Android.
      - `velvetflow-ios`: Platform-specific code for iOS.
      - Ktor Backend: REST APIs for file uploads, tagging, and metadata retrieval.
      ```

2. **Create API Documentation Template**
    - Start API documentation in `API.md` with an example route:
        - `/health`: Returns `VelvetFlow CMS is running`.

3. **Set Up Security Documentation**
    - Add plans for basic security:
        - IAM roles/policies for S3 uploads.
        - JWT for backend authentication.

---

## Week 2: Content Organization

### Learning Tasks
1. **File Upload Best Practices with AWS S3**
    - Learn how to securely upload files to S3 using pre-signed URLs and restrict file types/sizes.

2. **Content Metadata Management in PostgreSQL**
    - Study storage of metadata like tags, file type, and upload timestamps.

3. **Basic Security for File Uploads**
    - Learn how to enforce secure file upload restrictions.

4. **Content Organization Patterns**
    - Research folder hierarchy patterns for logical content grouping in S3.

---

### Development Tasks (3-hour Sunday block)

#### Hour 1: Core Development
1. **Develop Basic File Upload Functionality**
    - Create an upload route in Ktor that:
        - Generates pre-signed S3 URLs.
        - Accepts file metadata (e.g., tags).
        - Stores metadata in the PostgreSQL `Files` table.

2. **Create Folder Structure Logic**
    - Define a folder structure for S3 uploads:
        - `/uploads/{userId}/{fileType}/filename`.

3. **Implement Basic Tagging System**
    - Add a `tags` field to the PostgreSQL `Files` table.
    - Include logic to save tags alongside file uploads.

---

#### Hour 2: Testing
1. **Test File Upload Functionality**
    - Test pre-signed S3 uploads using Postman or cURL.
    - Ensure files are stored in the correct folder structure.

2. **Validate Content Organization Structure**
    - Query the PostgreSQL `Files` table and verify:
        - Correct folder paths.
        - Tagging logic works.

3. **Security Validation of Upload Process**
    - Test invalid uploads:
        - Restrict unsupported file types (e.g., `.exe`).
        - Enforce file size limits.

---

#### Hour 3: UI and Documentation
1. **Build Content Grid UI**
    - Develop a basic UI (in `velvetflow-shared`) to display uploaded files in a grid format.

2. **Implement Metadata Extraction**
    - Use file metadata (e.g., size, tags, timestamps) to populate the content grid.

3. **Update Documentation**
    - Update API documentation for:
        - File upload route (`POST /api/upload`):
            - Accepts metadata.
            - Returns a pre-signed URL.
        - Metadata retrieval route (`GET /api/files`):
            - Retrieves file information for the grid.
    - Add details of S3 folder structure and tagging implementation.

---

## Key Milestones for VelvetFlow CMS
By the end of Week 2, the following should be completed:
1. **File Upload and Organization**
    - Files can be uploaded securely to the `velvetflow-cms-storage` bucket.
    - Metadata (tags, timestamps, etc.) is stored in PostgreSQL.
    - Files are organized under the folder hierarchy.

2. **UI**
    - Basic content grid UI displays uploaded files with metadata.

3. **Documentation**
    - Complete overview of VelvetFlow CMS:
        - Setup process.
        - API definitions.
        - Folder structure and file organization details.
