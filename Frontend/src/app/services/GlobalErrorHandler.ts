import { Router } from '@angular/router';
import { ErrorHandler, Injectable, Injector } from '@angular/core';
@Injectable()
export class GlobalErrorHandler implements ErrorHandler {
  constructor(private injector: Injector) { }
  handleError(error) {
    const router = this.injector.get(Router);
    // console.error(error.stack.toString());
  }
}