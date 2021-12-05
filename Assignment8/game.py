import pygame
from pygame.locals import *
from time import sleep
import random

class Sprite():
    def __init__(self, x, y, w, h):
        self.x = x
        self.y = y
        self.w = w
        self.h = h

        self.image = None
        self.rect = None

        self.isMario = False
        self.isCoin = False
        self.isBrick = False
        self.isCoinBlock = False

    def update(self):
        pass
    def draw(self):
        v.screen.blit(self.image, (self.x - m.cameraPos,self.y, self.w, self.h))

class Coin(Sprite):
    def __init__(self,x,y,w,h):
        super().__init__(x,y,w,h)
        self.dy = random.randrange(1,10)
        self.image = pygame.image.load("coin.png")

    def update(self):
        if(self.y > 1000):
            m.sprites.remove(self)
        self.x += self.dy
        self.y += 5

class Mario(Sprite):
    def __init__(self,x,y,w,h):
        super().__init__(x,y,w,h)
        self.box = False
        self.oldX = x
        self.oldY = y
        self.mario_images = []
        self.image_index = 0
        self.isMario = True
        self.vert_velocity = 0
        self.jump_frame = 0
        for i in range(1,5,1):
            self.mario_images.append(pygame.image.load("mario" + str(i) + ".png"))
        self.image = self.mario_images[0]
    
    def updatePos(self):
        self.oldX = self.x + m.cameraPos
        self.oldY = self.y

    def update(self):
        self.vert_velocity += 2.1
        self.y += self.vert_velocity
        if(self.image_index >= 4):
            self.image_index = 0
        self.image = self.mario_images[self.image_index]
        for i in m.sprites:
            if(isinstance(i,Brick) and self.collision(i)):
                self.getOut(i)
        if(self.y + self.h > 600):
            self.y = 600 - self.h
            self.vert_velocity = 0
            self.jump_frame = 0
        self.updatePos()
        self.jump_frame += 1

    def jump(self):
        self.vert_velocity -= 7
        
    def draw(self):
        v.screen.blit(self.image, (200,self.y, self.w, self.h))
        if(self.box):
            pygame.draw.rect(v.screen, [255,255,125], [200, self.y, self.w, self.h], 0)

    def collision(self, b):
        # brick to head
        if(self.y > (b.y + b.h)):
            return False
        # brick to feet
        if((self.y + self.h) < b.y):
            return False
        # mario left brick right
        if(self.x > (b.x + b.w) - m.cameraPos):
            return False
        # mario right brick left
        if((self.x + self.w) <  (b.x - m.cameraPos)):
            return False
        return True
        
    def getOut(self, temp):
        if(((self.x + self.w) + m.cameraPos >= temp.x) and ((self.oldX + self.w) < temp.x)):
            m.cameraPos -= 8
        elif(((self.x + m.cameraPos) <= (temp.x + temp.w)) and (self.oldX > (temp.x + temp.w))):
            m.cameraPos += 8
        elif(self.y <= (temp.y + temp.h) and (self.oldY > temp.y)):
            self.y = temp.y + temp.h + 2
            self.vert_velocity = 10
            if(temp.isCoinBlock):
                temp.addCoin()
        elif((self.y + self.h >= temp.y) and (self.oldY + self.h < (temp.y + temp.h))):
            self.y = temp.y - self.h - 2
            self.vert_velocity = 0
            self.jump_frame = 0

class Brick(Sprite):
    def __init__(self, x, y, w, h):
        super().__init__(x,y,w,h)
        self.coinCounter = 0
        self.isCoinBlock = (random.randrange(0,10) % 2 == 0)
        if(self.isCoinBlock):
            self.image = pygame.image.load("coinblock.png")
        else:
            self.image = pygame.image.load("brick.png")
        self.image = pygame.transform.scale(self.image, (self.h, self.w))
        self.rect = self.image.get_rect()

    def addCoin(self):
        self.coinCounter += 1
        m.sprites.append(Coin(self.x + (self.w / 2), self.y, 30,30))
        if self.coinCounter >= 5:
            self.isCoinBlock = False
            self.image = pygame.image.load("brick.png")
            self.image = pygame.transform.scale(self.image, (self.h, self.w))

class Model():
    def __init__(self):
        self.sprites = []
        self.cameraPos = 0
        self.mario = Mario(200,200,60,95)
        self.sprites.append(self.mario)

    def update(self):
        for i in self.sprites:
            i.update()

class View():
    def __init__(self, model):
        screen_size = (1000,800)
        self.screen = pygame.display.set_mode(screen_size, 32)
        self.background = pygame.image.load("background.png")
        self.model = model

    def update(self):
        # draw background and grass
        self.screen.fill([109,207,247])
        self.screen.blit(self.background, (-(m.cameraPos * 0.5),0))
        pygame.draw.rect(self.screen, [0,255,0], [0, 600, 1000, 200], 0)

        # draw all sprites
        for i in m.sprites:
            i.draw()
        pygame.display.flip()


class Controller():
    def __init__(self, model):
        self.model = model
        self.keep_going = True

    def update(self):
        for event in pygame.event.get():
            if event.type == QUIT:
                self.keep_going = False
            elif event.type == KEYDOWN:
                if event.key == K_ESCAPE:
                    self.keep_going = False
        keys = pygame.key.get_pressed()
        if keys[K_LEFT]:
            self.model.cameraPos -= 8
            m.mario.image_index += 1
        if keys[K_RIGHT]:
            self.model.cameraPos += 8
            m.mario.image_index += 1
        if keys[K_UP] or keys[K_SPACE]:
            if(m.mario.jump_frame <= 6):
                m.mario.jump()
        if keys[K_b]:
            m.mario.box = not m.mario.box

print("Use the arrow keys to move. Press Esc to quit.")
pygame.init()

m = Model()
v = View(m)
c = Controller(m)

m.sprites.append(Brick(200 - m.cameraPos, 525, 75, 75))
m.sprites.append(Brick(275 - m.cameraPos, 300, 75, 75))
m.sprites.append(Brick(400 - m.cameraPos, 525, 75, 75))
m.sprites.append(Brick(600 - m.cameraPos, 525, 75, 75))

print(isinstance(m.sprites[0],Sprite))
while c.keep_going:
    c.update()
    m.update()
    v.update()
    
    sleep(0.04)

print("BYE")