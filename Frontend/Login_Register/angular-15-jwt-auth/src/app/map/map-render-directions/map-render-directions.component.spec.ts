import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MapRenderDirectionsComponent } from './map-render-directions.component';

describe('MapRenderDirectionsComponent', () => {
  let component: MapRenderDirectionsComponent;
  let fixture: ComponentFixture<MapRenderDirectionsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MapRenderDirectionsComponent]
    });
    fixture = TestBed.createComponent(MapRenderDirectionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
