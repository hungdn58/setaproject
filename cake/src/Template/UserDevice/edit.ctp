<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Form->postLink(
                __('Delete'),
                ['action' => 'delete', $userDevice->userID],
                ['confirm' => __('Are you sure you want to delete # {0}?', $userDevice->userID)]
            )
        ?></li>
        <li><?= $this->Html->link(__('List User Device'), ['action' => 'index']) ?></li>
    </ul>
</nav>
<div class="userDevice form large-9 medium-8 columns content">
    <?= $this->Form->create($userDevice) ?>
    <fieldset>
        <legend><?= __('Edit User Device') ?></legend>
        <?php
            echo $this->Form->input('deviceToken');
            echo $this->Form->input('createDate', ['empty' => true]);
            echo $this->Form->input('updateDate', ['empty' => true]);
            echo $this->Form->input('delFlg');
        ?>
    </fieldset>
    <?= $this->Form->button(__('Submit')) ?>
    <?= $this->Form->end() ?>
</div>
