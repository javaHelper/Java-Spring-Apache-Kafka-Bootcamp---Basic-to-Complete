/// <reference types="node" />
import http from 'http';
import express from 'express';
declare function timeoutMiddleware(server: http.Server, shutdownTimeout: number): {
    middleware(_request: express.Request, res: express.Response, next: express.NextFunction): void;
    cancel(): void;
};
export { timeoutMiddleware };
