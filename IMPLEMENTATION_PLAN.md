# Implementation Plan - Add WorkManager Tests

This plan outlines the steps to add automated tests for the WorkManager implementation in the Water Me app. This includes testing both the `WaterReminderWorker` and the `WorkManagerWaterRepository`.

## Proposed Changes

### Build Configuration

#### [app/build.gradle.kts](file:///Users/mark/StudioProjects/basic-android-kotlin-compose-training-waterme/app/build.gradle.kts)

- Add WorkManager testing dependency.
- Add standard Android test dependencies.

```kotlin
dependencies {
    // ...
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.work:work-testing:2.10.0")
}
```

---

### Tests

#### [NEW] [WaterReminderWorkerTest.kt](file:///Users/mark/StudioProjects/basic-android-kotlin-compose-training-waterme/app/src/androidTest/java/com/example/waterme/worker/WaterReminderWorkerTest.kt)

- Test that `WaterReminderWorker` returns success when given valid input data.

#### [NEW] [WorkManagerWaterRepositoryTest.kt](file:///Users/mark/StudioProjects/basic-android-kotlin-compose-training-waterme/app/src/androidTest/java/com/example/waterme/data/WorkManagerWaterRepositoryTest.kt)

- Test that `WorkManagerWaterRepository.scheduleReminder` correctly enqueues a work request with the expected delay and data.

## Verification Plan

### Automated Tests
- Sync Gradle to apply dependency changes.
- Run the new instrumented tests using the following command:
  ```bash
  ./gradlew connectedCheck
  ```
