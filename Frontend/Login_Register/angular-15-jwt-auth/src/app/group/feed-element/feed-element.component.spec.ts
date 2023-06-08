import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedElementComponent } from './feed-element.component';

describe('FeedElementComponent', () => {
  let component: FeedElementComponent;
  let fixture: ComponentFixture<FeedElementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FeedElementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FeedElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
