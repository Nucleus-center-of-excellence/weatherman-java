import { RouteReuseStrategy } from '@angular/router/';
import { ActivatedRouteSnapshot, DetachedRouteHandle } from '@angular/router';
export class CacheRouteReuseStrategy implements RouteReuseStrategy {
    storedRouteHandles = new Map<string, DetachedRouteHandle>();
    allowRetriveCache = {};
    CacheRouterList = [
        {
          fromroute: 'loginSignup',
          toroute: 'home'
        }
      ]

    constructor() {
             this.CacheRouterList.forEach(element => {
                 this.allowRetriveCache[element.toroute] = true;
             });
    }
    // This method called everytime when we navigate between routes. beforr(navigating from)and curr(navigating to).If it returns TRUE the routing will not happen.
    shouldReuseRoute(before: ActivatedRouteSnapshot, curr: ActivatedRouteSnapshot): boolean {
        this.CacheRouterList.forEach(element => {
            console.log(element);
            
        if (this.getPath(before) === element.fromroute && this.getPath(curr) === element.toroute) {
            this.allowRetriveCache[element.toroute] = true;
        } else {
            this.allowRetriveCache[element.toroute] = false;
        }
        });
        return before.routeConfig === curr.routeConfig;
    }

    // If this method returns TRUE then retrieve method will be called, otherwise the component will be created from scratch
    shouldAttach(route: ActivatedRouteSnapshot): boolean {
        const path = this.getPath(route);
        if (this.allowRetriveCache[path]) {
            return this.storedRouteHandles.has(this.getPath(route));
        }

        return false;
    }

    // This method is called if shouldAttach returns TRUE. using it to get any stored RouteHandle manually
    retrieve(route: ActivatedRouteSnapshot): DetachedRouteHandle | null {
        return this.storedRouteHandles.get(this.getPath(route)) as DetachedRouteHandle;
    }

    // It is invoked when we leave the current route. If returns TRUE then the store method will be invoked
    shouldDetach(route: ActivatedRouteSnapshot): boolean {
        const path = this.getPath(route);
        if (this.allowRetriveCache.hasOwnProperty(path)) {
            return true;
        }
        return false;
    }
    // This method is invoked only if the shouldDetach returns true.
    store(route: ActivatedRouteSnapshot, detachedTree: DetachedRouteHandle): void {
        this.storedRouteHandles.set(this.getPath(route), detachedTree);
    }
    private getPath(route: ActivatedRouteSnapshot): string {
        if (route.routeConfig !== null && route.routeConfig.path !== null) {
            return route.routeConfig.path;
        }
        return '';
    }



}