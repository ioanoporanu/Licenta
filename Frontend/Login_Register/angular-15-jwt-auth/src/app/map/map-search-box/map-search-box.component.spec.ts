import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MapSearchBoxComponent } from './map-search-box.component';

describe('MapSearchBoxComponent', () => {
  let component: MapSearchBoxComponent;
  let fixture: ComponentFixture<MapSearchBoxComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MapSearchBoxComponent]
    });
    fixture = TestBed.createComponent(MapSearchBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
