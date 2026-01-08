module.exports = {
  preset: 'ts-jest',
  testEnvironment: 'node',
  roots: ['<rootDir>/test'],
  testMatch: ['**/*.test.ts'],
  reporters: [
    'default',
    [
      'jest-html-reporters',
      {
        pageTitle: 'Santa Scheduling Documentation',
        outputPath: 'docs/test-documentation.html',
        includeFailureMsg: false,
        includeConsoleLog: true,
      },
    ],
  ],
};
