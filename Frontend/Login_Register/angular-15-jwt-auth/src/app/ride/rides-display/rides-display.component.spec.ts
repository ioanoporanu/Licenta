import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RidesDisplayComponent } from './rides-display.component';

describe('RidesDisplayComponent', () => {
  let component: RidesDisplayComponent;
  let fixture: ComponentFixture<RidesDisplayComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RidesDisplayComponent]
    });
    fixture = TestBed.createComponent(RidesDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
